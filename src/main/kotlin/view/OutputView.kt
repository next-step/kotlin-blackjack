package view

import domain.Participant
import domain.Player
import domain.Players

class OutputView {
    companion object {
        fun printPlayerNames() {
            println("게임에 참여할 사람의 이름을 입력하세요.")
        }

        fun printFirstCard(players: Players) {
            println("딜러와 ${players.players.map(Player::name).joinToString(", ")}에게 2장의 카드를 나누었습니다.")
        }

        fun printCardStatusOnFirstRound(participant: Participant) {
            println("${participant.name}카드: ${participant.getPublicCardsOnFirstRound()}")
        }

        fun printCardStatus(participant: Participant) {
            println("${participant.name}카드: ${participant.cards}")
        }

        fun printDoYouWantCard(player: Player) {
            println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        }

        fun printDealerMustGetCard() {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        }

        // 결과 출력 카드 합산한 결과까지
        fun printRoundResult(player: Player) {
            println("${player.name}카드: ${player.cards} - 결과: ${player.cards.calculateScore()}")
        }

        // 최종 승패 출력
        fun printFinalResult(
            players: Players,
            dealer: Player,
        ) {
            println("## 최종 승패")
            for (player in players.players) {
                val result =
                    when {
                        player.cards.isBust() -> "패"
                        dealer.cards.isBust() -> "승"
                        player.cards.calculateScore() > dealer.cards.calculateScore() -> "승"
                        player.cards.calculateScore() < dealer.cards.calculateScore() -> "패"
                        else -> "무승부"
                    }
                println("${player.name}: $result")
            }
        }
    }
}
