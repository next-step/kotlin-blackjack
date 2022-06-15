package dsl

data class Person(
  val name: String,
  val company: String,
  val skills: List<Skill>,
  val languages: List<Language>
)

// PersonBuilder 객체에 들어온 값을 순서대로 -> apply 시킨 후 -> Person 객체로 만든다.
fun introduce(builder: PersonBuilder.() -> Unit): Person {
  return PersonBuilder().apply(builder).build()
}

class PersonBuilder {
  private lateinit var name: String
  private lateinit var company: String
  private lateinit var skills: List<Skill>
  private lateinit var languages: List<Language>

  fun name(value: String) {
    name = value
  }

  fun company(value: String) {
    company = value
  }

  // skills에 들어온 값 ->  skillBuilder 객체의 apply 함수 실행 -> list로 만든다.
  fun skills(builder: SkillBuilder.() -> Unit) {
    skills = SkillBuilder().apply(builder).build()
  }

  // languages에 들어온 값 ->  languageBuilder 객체의 apply 함수 실행 -> list로 만든다.
  fun languages(builder: LanguageBuilder.() -> Unit) {
    languages = LanguageBuilder().apply(builder).build()
  }

  fun build(): Person {
    return Person(name, company, skills, languages)
  }
}