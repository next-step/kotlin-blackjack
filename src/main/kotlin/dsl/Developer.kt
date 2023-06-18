package dsl

data class Developer(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?
)

@DeveloperMarker
class DeveloperBuilder : Builder<Developer> {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    override fun build(): Developer = Developer(
        name = name,
        company = company,
        skills = skills,
        languages = languages
    )
}
