package kotlindsl

// ResumeBuilder에 있는 함수만 받는다. (property 도 가능!)
fun introduce(block: ResumeBuilder.() -> Unit): Resume = ResumeBuilder().apply(block).build()

data class Resume(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val languages: List<Language>
)

class ResumeBuilder {
    // 빈값 초기화 or lateinit 둘다 가능
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: List<Skill>
    private lateinit var languages: List<Language>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Resume = Resume(name, company, skills, languages)
}
