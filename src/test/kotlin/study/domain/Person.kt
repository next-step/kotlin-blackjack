package study.domain

import study.builder.PersonBuilder

data class Person(
    val name: String,
    val company: String?,
    val skills: List<String>,
    val languages: List<Language>,
) {
    companion object {
        fun introduce(block: PersonBuilder.() -> Unit): Person { // Person의 모든 함수를 받도록 설정
            return PersonBuilder().apply(block).build()
        }
    }
}
