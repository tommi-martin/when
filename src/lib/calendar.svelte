<script>
    import CalendarDate from "./calendarDate.svelte";

    let days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
    let date = new Date();
    let currentDate = date.getDate();
    let month = date.getMonth();
    let year = date.getFullYear();
    /**
     * @typedef {Object} DateObject
     * @property {String} day
     * @property {Number | null} date
     * @property {Boolean} current
     * @property {Boolean} selected
     * @property {Boolean} disabled
     */

    /**
     * @type Array<DateObject> dates
     */
    let dates = [];

    let firstDay = (new Date(year, month)).getDay();
    let daysInMonth = 32 - new Date(year, month, 32).getDate();

    // Adjust the firstDay to match with the new days array
    firstDay = (firstDay + 6) % 7;

    // Add empty days to the start of the month
    for (let i = 0; i < firstDay; i++) {
        dates.push({
            day: days[i],
            date: null,
            current: false,
            selected: false,
            disabled: true
        });
    }

    for (let i = 1; i <= daysInMonth; i++) {
        dates.push({
            day: days[(firstDay + i - 1) % 7],
            date: i,
            current: currentDate === i,
            selected: false,
            disabled: i < currentDate
        });
    }
</script>

<div class="container h-full mx-auto max-w-5xl mt-12">
    <h1 class="h1 mb-4">Calendar</h1>
    <div class="grid grid-cols-7 gap-4 mb-12">
        {#each dates as date}
            <CalendarDate {...date} />
        {/each}
    </div>
</div>
