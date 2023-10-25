package sistemasOperacionais;

public class RoundRobin implements Runnable {
	private final String Nome;
	private final int TempoExec;
	private final int Quantum;
	private final Object Lock;

	public RoundRobin(String name, int executionTime, int quantum, Object lock) {
		this.Nome = name;
		this.TempoExec = executionTime;
		this.Quantum = quantum;
		this.Lock = lock;
	}

	@Override
	public void run() {
		synchronized (Lock) {
			for (int i = 1; i <= TempoExec; i++) {
				System.out.println("Executando " + Nome + " no tempo " + i);

				if (i % Quantum == 0 && i < TempoExec) {
					try {
						System.out.println("Pausando " + Nome + " no tempo " + i);
						Thread.sleep(1000);
						Lock.notify(); // Notifica a próxima thread para iniciar
						Lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println(Nome + " execução completa.");
			Lock.notify(); // Notifica a próxima thread para iniciar
		}
	}
}