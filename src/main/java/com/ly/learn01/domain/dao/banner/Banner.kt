package com.ly.learn01.domain.dao.banner

import java.io.Serializable
import javax.persistence.*

/**
 * banner
 */

@Entity
data class Banner(@Column
             var title: String? = null) :Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}