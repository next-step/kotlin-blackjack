package study

import dsl.Person
import dsl.Skill
import dsl.introduce
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "제이슨"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skill() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills!!.size shouldBe 3
        person.skills!![0].shouldBeTypeOf<Skill.Soft>()
        person.skills!![0].detail shouldBe "A passion for problem solving"
        person.skills!![1].shouldBeTypeOf<Skill.Soft>()
        person.skills!![1].detail shouldBe "Good communication skills"
        person.skills!![2].shouldBeTypeOf<Skill.Hard>()
        person.skills!![2].detail shouldBe "Kotlin"
    }

    @Test
    fun isSameSkill() {
        val person1 = introduce {
            name("홍길동")
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
        }
        val person2 = introduce {
            name("홍길동")
            skills {
                soft("A passion for problem solving")
                hard("Kotlin")
            }
        }

        // 테스트이므로 !! 사용
        person1.skills!![0] shouldBe person2.skills!![0]
        person1.skills!![1] shouldBe person2.skills!![1]
    }

    @Test
    fun language() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        // 테스트이므로 !! 사용
        person.languages!!.size shouldBe 2
        person.languages!![0].level shouldBe 5
        person.languages!![0].language shouldBe "Korean"
        person.languages!![1].level shouldBe 3
        person.languages!![1].language shouldBe "English"
    }

    @Test
    fun total() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
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
        person.name shouldBe "홍길동"

        person.company shouldBe "활빈당"

        // 테스트이므로 !! 사용
        person.skills!!.size shouldBe 3
        person.skills!![0].shouldBeTypeOf<Skill.Soft>()
        person.skills!![0].detail shouldBe "A passion for problem solving"
        person.skills!![1].shouldBeTypeOf<Skill.Soft>()
        person.skills!![1].detail shouldBe "Good communication skills"
        person.skills!![2].shouldBeTypeOf<Skill.Hard>()
        person.skills!![2].detail shouldBe "Kotlin"

        person.languages!!.size shouldBe 2
        person.languages!![0].language shouldBe "Korean"
        person.languages!![0].level shouldBe 5
        person.languages!![1].language shouldBe "English"
        person.languages!![1].level shouldBe 3
    }
}
