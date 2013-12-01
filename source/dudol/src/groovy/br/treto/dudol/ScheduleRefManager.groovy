package br.treto.dudol

import java.util.concurrent.ScheduledFuture

class ScheduleRefManager {
	
	private static Map<String,ScheduledFuture> refs = new HashMap<String,ScheduledFuture>()

	public static add(String key, ScheduledFuture handle) {
		refs.put(key, handle)

        for (s in Schedule.findAll()) {
            println s.key
        }
	}

	public static cancel(String key) {
		refs.get(key).cancel(true)
		refs.remove(key)
	}
}