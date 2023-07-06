package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.FriendResponse;
import com.peaksoft.giftlistm5.model.Notification;
import com.peaksoft.giftlistm5.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendController {
    private final FriendService friendService;
    @PutMapping("/addFriend/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String updateFriend(@PathVariable("id") Long userId, Principal principal) {
        return friendService.addFriend(userId, principal);
    }
    @DeleteMapping("/deleteFriend/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String deleteFriend(@PathVariable("id") Long userId, Principal principal) {
        return friendService.deleteFriend(userId, principal);
    }
    @PutMapping("/replyFriend/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String replyForFriendship(@PathVariable("id") Long notificationId,
                                     Principal principal,
                                     @RequestParam Boolean bool) {// true если примет false если нет
        return friendService.replyFriend(notificationId, principal, bool);
    }
    @GetMapping("/getFriends")
    public Set<FriendResponse> getAllFriends(Principal principal) {
        return friendService.getAllFriends(principal);
    }
    @GetMapping("/getFriendshipRequests")
    //    @PreAuthorize("hasAuthority('USER')")
    public List<Notification> getAllRequestsForFriendship(Principal principal) {
        return friendService.getAllRequestsForFriendship(principal);
    }
    @GetMapping("/getFriend/{id}")
    //    @PreAuthorize("hasAuthority('USER')")
    public FriendResponse getFriend(@PathVariable("id") Long friendId) {
        return friendService.getFriendById(friendId);
    }
}