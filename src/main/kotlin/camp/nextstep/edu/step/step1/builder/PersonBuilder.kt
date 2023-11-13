package camp.nextstep.edu.step.step1.builder

import camp.nextstep.edu.step.step1.domain.Company
import camp.nextstep.edu.step.step1.domain.Language
import camp.nextstep.edu.step.step1.domain.Person
import camp.nextstep.edu.step.step1.domain.Skill

class PersonBuilder {

    private lateinit var name: String
    private val skills = mutableListOf<Skill>()
    private var company: Company? = null
    private val languages = mutableListOf<Language>()

    fun name(name: String) {
        this.name = name
    }

    fun skill(block: SkillBuilder.() -> Unit) {
        val skillBuilder = SkillBuilder()
        skillBuilder.block()
        skills.addAll(skillBuilder.build())
    }

    fun language(block: LanguageBuilder.() -> Unit) {
        val languageBuilder = LanguageBuilder()
        languageBuilder.block()
        languages.addAll(languageBuilder.build())
    }

    fun company(block: CompanyBuilder .() -> Unit) {
        val companyBuilder = CompanyBuilder()
        companyBuilder.block()
        company = companyBuilder.build()
    }

    fun build(): Person {
        return Person(
            name = name,
            company = company!!,
            skill = skills,
            language = languages
        )
    }

}
