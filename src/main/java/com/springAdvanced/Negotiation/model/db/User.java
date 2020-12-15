package com.springAdvanced.Negotiation.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public User(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    // @JsonProperty
    @Column
    private String first_name;
    //  @JsonProperty
    @Column
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

    public com.springAdvanced.Negotiation.model.json.User convertToJsonUser() {
        com.springAdvanced.Negotiation.model.json.User jsonUser = new com.springAdvanced.Negotiation.model.json.User();

        if (id != null) {
            jsonUser.setId(id);
        }
        if (first_name != null) {
            jsonUser.setFirst_name(first_name);
        }
        if (last_name != null) {
            jsonUser.setLast_name(last_name);
        }
        return jsonUser;
    }
}