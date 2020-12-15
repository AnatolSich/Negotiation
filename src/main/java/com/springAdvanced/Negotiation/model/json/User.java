package com.springAdvanced.Negotiation.model.json;

import com.springAdvanced.Negotiation.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String first_name;
    private String last_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public com.springAdvanced.Negotiation.model.db.User convertToDBUser(UserRepository userRepository) {
        com.springAdvanced.Negotiation.model.db.User jpaUser = Optional.ofNullable(id).map(
                id -> userRepository.findById(id)
                        .orElse(new com.springAdvanced.Negotiation.model.db.User()))
                .orElse(new com.springAdvanced.Negotiation.model.db.User());

        if (first_name != null) {
            jpaUser.setFirst_name(first_name);
        }
        if (last_name != null) {
            jpaUser.setLast_name(last_name);
        }
        return jpaUser;
    }
    public com.springAdvanced.Negotiation.model.db.User convertToDBUser() {
        com.springAdvanced.Negotiation.model.db.User jpaUser = new com.springAdvanced.Negotiation.model.db.User(id);

        jpaUser.setFirst_name(emptyIfNull(first_name));
        jpaUser.setLast_name(emptyIfNull(last_name));
        return jpaUser;
    }

    private String emptyIfNull(String string) {
        return string != null ? string : "";
    }
}