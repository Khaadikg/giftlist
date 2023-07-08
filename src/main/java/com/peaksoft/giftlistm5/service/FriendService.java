package com.peaksoft.giftlistm5.service;

import com.peaksoft.giftlistm5.dto.FriendResponse;
import com.peaksoft.giftlistm5.enums.NotificationType;
import com.peaksoft.giftlistm5.exception.NotFoundException;
import com.peaksoft.giftlistm5.exception.UserAlreadyExistException;
import com.peaksoft.giftlistm5.model.Notification;
import com.peaksoft.giftlistm5.model.User;
import com.peaksoft.giftlistm5.repository.NotificationRepository;
import com.peaksoft.giftlistm5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final NotificationRepository notificationRepository;
    public String addFriend(Long id, Principal principal) {
        User owner = getAuthorizedUser(principal);
        if (owner.getId().equals(id)) {
            throw new UserAlreadyExistException("You send friendship to yourself");
        }
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("There is no user by id = " + id));
        if (CollectionUtils.isEmpty(user.getNotifications())) {
            user.setNotifications(new ArrayList<>());
        }
        if (CollectionUtils.isEmpty(user.getReceiving())) {
            user.setReceiving(new ArrayList<>());
        }
        List<Notification> notifications = user.getReceiving();
        notifications.add(defaultAddFriendNotification(owner, user));
        user.setNotifications(notifications);
        userRepository.save(user);
        notificationRepository.saveAll(user.getReceiving());
        return "Sent request for friendship to "
                + user.getFirstName() + " " + user.getLastName();
    }
    public String deleteFriend(Long id, Principal principal) {
        User owner = getAuthorizedUser(principal);
        User friend = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("There is no user by id = " + id)
        );
        if (!owner.getFriends().contains(friend)) {
            log.error("You dont have friend by id = " + id);
            throw new NotFoundException("You dont have friend by id = " + id);
        } else {
            owner.getFriends().remove(friend);
            friend.getFriends().remove(owner);
            userRepository.save(friend);
            userRepository.save(owner);
            return "Friend deleted!";
        }
    }
    public Notification defaultAddFriendNotification(User owner, User receiver) {// не используется в репо логике
        return Notification.builder()
                        .receiver(receiver)
                        .message("С вами хочет подружиться пользователь "
                                + owner.getFirstName() + " " + owner.getLastName())
                        .createdDate(LocalDate.now())
                        .type(NotificationType.ASK_FOR_FRIENDSHIP)
                        .owner(owner).build();
    }
    public Notification defaultReplyFriendNotification(User receiver, Boolean accept) {// не используется в репо логике
        String replyMessage = (accept) ? " принял" : " отклонил";
        User user = getAuthorizedUser(SecurityContextHolder.getContext().getAuthentication());
        return notificationRepository.save(
                Notification.builder()
                        .message("Пользователь "+ user.getFirstName() + user.getLastName()
                                + replyMessage + " ваш запрос в друзья!")
                        .createdDate(LocalDate.now())
                        .type(NotificationType.REPLY_FRIENDSHIP)
                        .receiver(receiver)
                        .owner(user).build()
        );
    }
    public String replyFriend(Long notificationId, Principal principal, Boolean bool) {
        User replier = getAuthorizedUser(principal);
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(
                () -> new NotFoundException("There is no notification by id = " + notificationId)
        );
        User sender = userRepository.findByEmail(notification.getOwner().getEmail()).orElseThrow(
                () -> new NotFoundException("User not found in notification!")
        );
        if (!bool) {
                sender.getReceiving()
                        .add(defaultReplyFriendNotification(replier, false));
                userRepository.save(sender);
                replier.getReceiving().removeIf(
                        notification2 -> notification2.getId().equals(notificationId)
                );
                notificationRepository.deleteById(notificationId);
                userRepository.save(replier);
            return "Пользователь отклонил дружбу!";
        }
        else {
            replier.getFriends().add(sender);
            sender.getFriends().add(replier);
            replier.getReceiving().removeIf(
                    notification2 -> notification2.getId().equals(notificationId)
            );
            sender.getReceiving()
                    .add(defaultReplyFriendNotification(sender, true));
            notificationRepository.deleteById(notificationId);
        }
        return "Пользователь принял дружбу!";
    }
    public Set<FriendResponse> getAllFriends(Principal principal) {
        User user = getAuthorizedUser(principal);
        List<User> list = userRepository.findAllById(user.getId()).orElseThrow(
                () -> new NotFoundException("There is no friends found by id = " + user.getId())
        );
        Set<FriendResponse> out = new HashSet<>();
        for (User user1 : list) {
            out.add(mapToResponse(user1));
        }
        return out;
    }
    public List<Notification> getAllRequestsForFriendship(Principal principal) {
        User user = getAuthorizedUser(principal);
        List<Notification> notes = new ArrayList<>();
            for (Notification n : userRepository.getReferenceById(user.getId()).getReceiving()){
                if (n.getType().equals(NotificationType.ASK_FOR_FRIENDSHIP)) {
                    notes.add(n);
                }
            }
        return notes;
    }
    public FriendResponse getFriendById(Long friendId) {
        return mapToResponse(userRepository.findById(friendId).orElseThrow(
                () -> new NotFoundException("No user by id = " + friendId)
        ));
    }
    public FriendResponse mapToResponse(User user) {
        return FriendResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mailing(user.getMailing()).build();
    }
    public User getAuthorizedUser(Principal principal) {
        if (principal == null) throw new NotFoundException("There is no principal!");
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("There is no authorized user provided by email = " + principal.getName()));
    }
}