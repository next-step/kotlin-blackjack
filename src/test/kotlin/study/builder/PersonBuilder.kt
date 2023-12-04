package study.builder

import study.model.Person

/**
 * 사람 객체 빌더
 * */
class PersonBuilder {

    private var name: String = DEFAULT_NAME
    private var company: String = DEFAULT_COMPANY
    private val skillBuilder: SkillBuilder = SkillBuilder()
    private val languageBuilder: LanguageBuilder = LanguageBuilder()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skillBuilder.block()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languageBuilder.block()
    }

    fun build() = Person(name, company, skillBuilder.build(), languageBuilder.build())

    companion object {
        private const val DEFAULT_NAME = ""
        private const val DEFAULT_COMPANY = ""
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
