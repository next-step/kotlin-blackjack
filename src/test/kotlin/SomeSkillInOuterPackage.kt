import study.Skill

class SomeSkillInOuterPackage(val description: String)
//    : Skill {}  // Skill은 sealed interface이기 때문에, 동일한 패키지/모듈 내에서만 구현할 수 있음.
// 나타나는 경고 메세지: Inheritor of sealed class or interface declared in package <root> but it must be in package study where base class is declared