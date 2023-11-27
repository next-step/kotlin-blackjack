package blackjack.domain.component

class BlackjackInputValidator {
    fun validatePlayerNamesString(playerNames: String?): String {
        require(!playerNames.isNullOrBlank()) { "플레이어 이름은 null 또는 공백일 수 없습니다." }

        return playerNames
    }

    fun validatePlayerNamesSize(playerNames: List<String>): List<String> {
        require(playerNames.isNotEmpty()) { "게임에는 1명 이상의 플레이어가 참가해야합니다." }

        return playerNames
    }

    fun validateYesNoString(yesNo: String?): String {
        require(!yesNo.isNullOrBlank()) { "힛은 null 또는 공백일 수 없습니다." }

        return yesNo
    }
}
