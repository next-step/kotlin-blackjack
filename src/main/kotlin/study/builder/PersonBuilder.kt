package study.builder

import study.domain.Person
import study.domain.Skill
import study.domain.Skills

class PersonBuilder() {
    private var name: String = ""
    private var company: String = ""
    var skills: Skills = Skills()
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: Skills.() -> Unit) {
        skills = Skills().apply(block)
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}
