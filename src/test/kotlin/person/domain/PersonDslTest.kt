package person.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class PersonDslTest : StringSpec({
    "introduce는 Person을 반환한다." {
        PersonDsl.introduce {
            name("김성민")
            company("넥스트스텝")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {}
        }.shouldBeInstanceOf<Person>()
    }
})
