package util;

// class custom buat handle errors dengan jelas dan konsisten
// inspired dari error systemnya rust
public class Result<T, E> {
	private final T value; // success value
	private final E error; // error value

	private Result(T value, E error) {
		this.value = value;
		this.error = error;
	}

	// static factory methods buat bikin result dengan gampang
	public static <T, E> Result<T, E> ok(T value) {
		return new Result<>(value, null);
	}

	public static <T, E> Result<T, E> err(E error) {
		return new Result<>(null, error);
	}

	// methods untuk cek error ato ngga dengan gampang
	public boolean isOk() {
		return value != null;
	}

	public boolean isErr() {
		return error != null;
	}

	// getters dengan error handling yang lengkap
	public T getValue() {
		if (!isOk()) {
			throw new IllegalStateException("Result is an Err");
		}
		return value;
	}

	public E getError() {
		if (!isErr()) {
			throw new IllegalStateException("Result is an Ok");
		}
		return error;
	}
}
