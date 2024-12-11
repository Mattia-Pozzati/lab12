package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {
	
	private List<Integer> values;

	public LogicsImpl(final int size){
		values = new ArrayList<>(Collections.nCopies(size, 0));
	}

	@Override
	public int size() {
		return this.values.size();
	}

	@Override
	public List<Integer> values() {
		return values;
	}

	@Override
	public List<Boolean> enablings() {
		return values.stream()
				.map(s -> s < this.size())
				.toList();
	}

	@Override
	public int hit(int elem) {
		int prevVal = values().get(elem);
		int newVal = prevVal + 1;
		if( newVal > this.size()){
			return prevVal;
		}
		values.set(elem, newVal);
		return newVal;
	}

	@Override
	public String result() {
		return this.values().stream()
				.map(String::valueOf)
				.collect(Collectors.joining("|", "<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		return values().stream()
				.allMatch(s -> s.equals(values().getFirst()));
	}
}
