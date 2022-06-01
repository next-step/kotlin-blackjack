package study

data class Person(
    val name: String,
    val company: String,
    val skills: List<Skill>,
    val languages: List<Language>,
) {

    companion object {

        inline fun introduce(block: PersonBuilder.() -> Unit): Person =
            PersonBuilder()
                .apply(block)
                .build()

        class PersonBuilder {
            private lateinit var name: String
            private var company = ""
            private var skills = listOf<Skill>()
            private var languages = listOf<Language>()

            fun name(value: String) {
                name = value
            }

            fun company(value: String) {
                company = value
            }

            fun skills(block: Skill.Companion.SkillBuilder.() -> Unit) {
                skills = (Skill.Companion.SkillBuilder().apply(block).build())
            }

            fun languages(block: Language.Companion.LanguageBuilder.() -> Unit) {
                languages = (Language.Companion.LanguageBuilder().apply(block).build())
            }

            fun build() = Person(name, company, skills, languages)
        }
    }
}
