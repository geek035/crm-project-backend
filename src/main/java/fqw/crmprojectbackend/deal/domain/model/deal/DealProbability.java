package fqw.crmprojectbackend.deal.domain.model.deal;

public record DealProbability(Integer value) {
    public DealProbability {
        if (value == null) {
            value = 0;
        }

        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Вероятность сделки должна быть в диапазоне от 0 до 100");
        }
    }
}
