package resume

import resume.builder.PersonalInformationBuilder
import resume.model.Person

fun introduce(block: PersonalInformationBuilder.() -> Unit): Person {
    return PersonalInformationBuilder().apply(block).build()
}
