package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*introduce {
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
}*/
class DslTest {
    @ValueSource(strings = ["홍길동", "짐배"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
    }

    @Test
    fun skills() {
        val person = introduce {
            name("박재성")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills.hard shouldBe listOf("Kotlin")
    }

    @Test
    fun language() {
        val person = introduce {
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
        person.languages["Korean"] shouldBe 5
        person.languages["English"] shouldBe 3
    }

    @Test
    fun pair() {
        val pair1 = Pair(1, "to")
        val pair2 = 1 to "one"
        val pair3 = 1 of "one"
    }
}

infix fun Int.of(value: String): Pair<Int, String> = Pair(this, value)

operator fun Person.Languages.get(key: String): Int? {
    return languages[key]
}

fun introduce(block: Person.() -> Unit): Person {
    // let, also, apply, with, run
    return Person().apply(block)
}

class Person {
    lateinit var name: String
    lateinit var company: String
    var skills = Skills()
    var languages = Languages()
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: Skills.() -> Unit): Skills {
        // let, also, apply, with, run
        return skills.apply(block)
    }

    fun languages(block: Languages.() -> Unit): Languages {
        return languages.apply(block)
    }

    class Skills {
        var soft = mutableListOf<String>()
        var hard = mutableListOf<String>()

        fun soft(value: String) {
            soft.add(value)
        }

        fun hard(value: String) {
            hard.add(value)
        }
    }

    class Languages {
        var languages = mutableMapOf<String, Int>()
        infix fun String.level(level: Int) {
            languages[this] = level
        }
    }
}