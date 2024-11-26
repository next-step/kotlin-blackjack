package study.domain

import study.builder.SkillsBuilder

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

    class PersonBuilder {
        private lateinit var name: String
        private var company: String? = null // 회사가 없을 수도 있음
        private var skills: List<String> = emptyList()
        private var languages: List<Language> = emptyList()

        fun name(value: String) {
            this.name = value
        }

        fun company(value: String) {
            this.company = value
        }

        fun skills(block: SkillsBuilder.() -> Unit) {
            this.skills = SkillsBuilder().apply(block).build()
        }

        fun languages(vararg languages: Language) {
            this.languages = languages.toList()
        }

        fun build(): Person {
            return Person(name, company, skills, languages)
        }
    }
}
