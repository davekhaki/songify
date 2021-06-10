package com.songify.api.service;

import java.util.Optional;

public interface ChatRoomService {
    Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist);
}
