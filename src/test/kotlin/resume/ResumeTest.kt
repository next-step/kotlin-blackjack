package resume

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

// introduce {
//     name("박재성")
//     company("우아한형제들")
//     skills {
//         soft ("A passion for problem solving")
//         soft ("Good communication skills")
//         hard ("Kotlin")
//     }
//     languages {
//         "Korean" level 5
//         "English" level 3
//     }
// }
class ResumeTest {
    @Test
    internal fun name() {
        val person: Person = introduce {
            name("김광수")
        }

        assertThat(person.name).isEqualTo("김광수")
    }
}
