package blackjack.ui

import blackjack.entity.Player

class GetResult {

  fun informGameStart(names: List<String>){
    println(names.toString() + "에게 " + "2장을 나누었습니다.")
  }

  fun printPlayerStatus(player: Player){
    val cardStatus = player.wallet.cards.joinToString(", ") { card -> card.number.value.toString() + card.shape.value }
    println(player.name+ "카드: " + cardStatus )
  }

  fun printPlayerStatusWithResult(player: Player){
    val cardStatus = player.wallet.cards.joinToString(", ") { card -> card.number.value.toString() + card.shape.value }
    println(player.name+ "카드: " + cardStatus + " - 결과: " + player.wallet.sumUp)
  }
}