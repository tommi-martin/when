<script>
	import CalendarDate from "./calendarDate.svelte";

    let days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
	let date = new Date();
	let month = date.getMonth();
	let year = date.getFullYear();
	let dates = [];

	let firstDay = (new Date(year, month)).getDay();
	let daysInMonth = 32 - new Date(year, month, 32).getDate();

	for (let i = 1; i <= daysInMonth; i++) {
		dates.push({ day: days[(firstDay + i - 1) % 7], date: i });
	}
</script>

<div class="container h-full mx-auto">
    <h1 class="h1 mb-4">Calendar</h1>
    <div class="grid grid-cols-7 gap-4">
        {#each dates as { day, date }}
            <CalendarDate {day} {date} />
        {/each}
    </div>
</div>