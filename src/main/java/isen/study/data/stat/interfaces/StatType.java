package isen.study.data.stat.interfaces;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public interface StatType<T extends Stat> {
	T createStatistic();
}
