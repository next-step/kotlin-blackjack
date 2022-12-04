import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
  name("박재성")
  company("우아한형제들")
  skills {
    soft("A passion for problem solving")
    soft("Good communication skills")
    hard("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
 */
class DslTest {

    @Test
    fun introduceTest() {
        val person: Person = introduce {
            name("김찬수")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "김찬수"
        person.company shouldBe "회사"
        person.skills shouldContainAll listOf(
            Skill("A passion for problem solving", SkillType.SOFT),
            Skill("Good communication skills", SkillType.SOFT),
            Skill("Kotlin", SkillType.HARD),
        )
        person.langagues shouldContainAll listOf(
            Langauge("Korean", 5),
            Langauge("English", 3),
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: List<Skill>
    private lateinit var languages: List<Langauge>

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private var skills = mutableListOf<Skill>()
    fun soft(value: String) {
        skills.add(Skill(value, SkillType.SOFT))
    }

    fun hard(value: String) {
        skills.add(Skill(value, SkillType.HARD))
    }

    fun build() = skills.toList()
}

class LanguageBuilder {
    private var langagues = mutableListOf<Langauge>()

    infix fun String.level(value: Int) = langagues.add(Langauge(this, value))

    fun build() = langagues.toList()
}

data class Skill(val skill: String, val skillType: SkillType)

enum class SkillType { SOFT, HARD }

data class Langauge(val langauge: String, val level: Int)

data class Person(val name: String, val company: String, val skills: List<Skill>, val langagues: List<Langauge>)
