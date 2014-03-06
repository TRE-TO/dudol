package br.treto.dudol

import java.util.concurrent.*

class ScheduleRefManager {
	
	private static Map<String,ScheduledFuture> refs = new HashMap<String,ScheduledFuture>()

	private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5)

	public static start(Schedule schedule, Long startIn = 10) {
		ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			public void run() {
				Runtime.getRuntime().exec(schedule.executable.split())
    		}
		},
		startIn,
		new Long(schedule.rateInSeconds),
		TimeUnit.SECONDS)

		println "[DUDOL] Agendando tarefa '${schedule.key}' para início em ${startIn} segundos....."

		refs.put(schedule.key, scheduledFuture)
	}

	public static cancel(String key) {
		refs.get(key).cancel(true)
		refs.remove(key)
		println "[DUDOL] Tarefa '${key}' interrompida."
	}

	public static boolean isRunning(String key) {
		return refs.containsKey(key)
	}
}