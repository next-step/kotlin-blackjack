package study

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["히히히", "배고프다"])
    @ParameterizedTest
    fun name1() {
        val person = introduce {
            name("홍길동")
        }
        person.name shouldBe "홍길동"
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.company shouldBe "활빈당"
    }

    @Test
    fun hardSkills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                hard("레슬링")
            }
        }
        person.hardSkills shouldContain "레슬링"
    }

    @Test
    fun softSkills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("훈장님 훈계")
            }
        }
        person.softSkills shouldContain "훈장님 훈계"
    }

    @Test
    fun language() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("훈장님 훈계")
            }
            language {
                "Korean" level 5
            }
        }
        person.language["Korean"] shouldBe 5
    }
}

fun introduce(block: PersonBulider.() -> Unit): Person = PersonBulider().apply(block).build()

fun PersonBulider.skills(block: PersonBulider.() -> Unit) = block()

fun PersonBulider.language(block: PersonBulider.() -> Unit) = block()

data class Person(
    val name: String,
    val company: String?,
    val hardSkills: List<String>,
    val softSkills: List<String>,
    val language: Map<String, Int>
)

class PersonBulider {
    private lateinit var name: String
    private var company: String? = null
    private val hardSkills: MutableList<String> = mutableListOf()
    private val softSkills: MutableList<String> = mutableListOf()
    private val language: MutableMap<String, Int> = mutableMapOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun soft(value: String) {
        softSkills.add(value)
    }

    infix fun String.level(value: Int) {
        language[this] = value
    }

    fun build() = Person(name, company, hardSkills, softSkills, language)
}