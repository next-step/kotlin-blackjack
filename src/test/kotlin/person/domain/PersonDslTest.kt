package person.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class PersonDslTest : StringSpec({
    "introduce는 Person을 반환한다." {
        val person = PersonDsl.introduce {
            name("김성민")
            company("넥스트스텝")
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

        person.shouldBeInstanceOf<Person>()
        person.name shouldBe "김성민"
        person.company shouldBe "넥스트스텝"
        person.hardSkills shouldContainAll listOf("Kotlin")
        person.softSkills shouldContainAll listOf("A passion for problem solving", "Good communication skills")
        person.languages shouldContainAll (mapOf("Korean" to 5, "English" to 3))
    }
})
