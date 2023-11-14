package blackjack.domain

import blackjack.entity.Card
import blackjack.entity.CardNumber
import blackjack.entity.CardShape

object CardGenerator {
    // 이 함수는 정녕 테스트가 불가능한가?
    // 이전에는 canGo와 같이 할지 말지에 대한 boolean 체크지만, 이번 랜덤함수는 랜덤으로 하나를 뽑아내기때문에 어떻게 체크해야할까 고민이 되네요
    // 테스트 해야한다면 어떤걸 테스트해야할까
    fun generateCard(): Card {
        val cardNumber = CardNumber.values().toList().shuffled().first()
        val cardShape = CardShape.values().toList().shuffled().first()
        return Card(number = cardNumber, shape = cardShape)
    }
}
