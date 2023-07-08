package com.peaksoft.giftlistm5.controller;

import com.peaksoft.giftlistm5.dto.FriendResponse;
import com.peaksoft.giftlistm5.model.Notification;
import com.peaksoft.giftlistm5.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Tag(name = "Friends controller", description = "Using CRUD upon friends")
@RequestMapping("/api/friends")
public class FriendController {
    private final FriendService friendService;
    @PutMapping("/addFriend/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "Update", description = "Uses by users to add friends")
    public String addFriend(@PathVariable("id") Long userId, Principal principal) {
        return friendService.addFriend(userId, principal);
    }
    @DeleteMapping("/deleteFriend/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "Delete", description = "Uses by users to delete friends")
    public String deleteFriend(@PathVariable("id") Long userId, Principal principal) {
        return friendService.deleteFriend(userId, principal);
    }
    @PutMapping("/replyFriend/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "Reply", description = "Uses by users to reply for friendship request")
    public String replyForFriendship(@PathVariable("id") Long notificationId,
                                     Principal principal,
                                     @RequestParam Boolean bool) {// true если примет false если нет
        return friendService.replyFriend(notificationId, principal, bool);
    }
    @GetMapping("/getFriends")
    @Operation(summary = "Get all friends", description = "Uses by users to get all friends")
    public Set<FriendResponse> getAllFriends(Principal principal) {
        return friendService.getAllFriends(principal);
    }
    @GetMapping("/getFriendshipRequests")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get friendship requests", description = "Uses by users to get all requests for friendship")
    public List<Notification> getAllRequestsForFriendship(Principal principal) {
        return friendService.getAllRequestsForFriendship(principal);
    }
    @GetMapping("/getFriend/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get friend", description = "Uses by users to get friend by id")
    public FriendResponse getFriend(@PathVariable("id") Long friendId) {
        return friendService.getFriendById(friendId);
    }
}