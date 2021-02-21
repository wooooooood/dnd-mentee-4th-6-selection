package com.selection.dto;

import com.selection.security.oauth.AuthProvider;
import com.selection.domain.user.Role;
import com.selection.domain.user.User;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    public static final String SOCIAL_TYPE = "social_type";

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String email;
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, AuthProvider authProvider) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        attributes.put(SOCIAL_TYPE, authProvider);
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
        Map<String, Object> attributes) {

        if (AuthProvider.NAVER.name().equalsIgnoreCase(registrationId)) {
            return ofNaver("id", attributes);
        } else if (AuthProvider.KAKAO.name().equalsIgnoreCase(registrationId)) {
            return ofKakao("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName,
        Map<String, Object> attributes) {
        return OAuthAttributes.builder()
            .email((String) attributes.get("email"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .authProvider(AuthProvider.GOOGLE)
            .build();
    }

    @SuppressWarnings("unchecked")
    public static OAuthAttributes ofNaver(String userNameAttributeName,
        Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
            .email((String) response.get("email"))
            .attributes(response)
            .nameAttributeKey(userNameAttributeName)
            .authProvider(AuthProvider.NAVER)
            .build();
    }

    @SuppressWarnings("unchecked")
    public static OAuthAttributes ofKakao(String userNameAttributeName,
        Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        return OAuthAttributes.builder()
            .email((String)response.get("email"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .authProvider(AuthProvider.KAKAO)
            .build();
    }

    public User toEntity() {
        AuthProvider authProvider = (AuthProvider)attributes.get(SOCIAL_TYPE);
        return User.builder()
            .email(email)
            .provider(authProvider)
            .role(Role.USER)
            .build();
    }
}
