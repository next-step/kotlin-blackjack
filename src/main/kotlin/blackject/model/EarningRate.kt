package blackject.model

/**
 * 수익롤 관리 클래스
 * */
enum class EarningRate(val rate: Double) {
    AllBlackJack(1.0), BlackJack(1.5), Lose(-1.0), Win(1.0)
}
