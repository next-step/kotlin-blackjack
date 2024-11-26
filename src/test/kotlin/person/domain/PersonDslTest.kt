package person.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class PersonDslTest : StringSpec({

    "introduce는 Person을 반환한다." {
        PersonDsl.introduce {}.shouldBeInstanceOf<Person>()
    }

})
