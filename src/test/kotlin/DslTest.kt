import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
  name("최차영")
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
 */
class DslTest {
    @ValueSource(strings = ["최차영", "이든"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("최차영")
            company("회사")
        }
        assertThat(person.name).isEqualTo("최차영")
        assertThat(person.company).isEqualTo("회사")
    }

    @Test
    fun skills() {
        val skills =
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }

        assertThat(skills.size).isEqualTo(3)
    }
}

fun skills(block: SkillsBuilder.() -> Unit): List<String> {
    return SkillsBuilder().apply(block).build()
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

data class Person(val name: String, val company: String?)

class SkillsBuilder {
    private var skill: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        skill.add(value)
    }

    fun hard(value: String) {
        skill.add(value)
    }

    fun build(): List<String> {
        return skill
    }
}

data class Skills(val value: String)
