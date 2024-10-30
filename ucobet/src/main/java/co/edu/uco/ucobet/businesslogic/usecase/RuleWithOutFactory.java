package co.edu.uco.ucobet.businesslogic.usecase;

public interface RuleWithOutFactory<T> {
	void execute(T data);
}
