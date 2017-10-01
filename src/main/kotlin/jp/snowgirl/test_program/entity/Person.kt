package jp.snowgirl.test_program.entity

import org.seasar.doma.*

@Entity(immutable = true)
data class Person (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,
        val name: String,
        val age: Int?,
        val departmentId: Int
)