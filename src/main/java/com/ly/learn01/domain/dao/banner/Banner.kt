package com.ly.learn01.domain.dao.banner

import javax.persistence.*

/**
 * banner
 */

@Entity
class Banner(@Column
             var title: String? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}