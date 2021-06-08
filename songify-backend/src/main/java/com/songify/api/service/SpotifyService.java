package com.songify.api.service;

import javax.servlet.http.HttpServletResponse;

public interface SpotifyService {
    void authenticate(String code, HttpServletResponse httpServletResponse);
}
