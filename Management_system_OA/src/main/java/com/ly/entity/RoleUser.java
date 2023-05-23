package com.ly.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleUser implements Serializable {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * role_id
    */
    private Long rid;

    /**
    * user_id
    */
    private Long uid;

    private static final long serialVersionUID = 1L;
}