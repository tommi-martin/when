<script>
    let eventName = '';
    let eventDescription = '';
    let startDate = 0;
    let endDate = 0;
</script>
<form action="#" class="max-w-md mx-auto">
    <label for="eventName" class="label">Event Name:</label>
    <input bind:value={eventName} type="text" id="eventName" name="eventName" class="input mb-4">
    <label for="eventDescription" class="label">Event Description:</label>
    <input bind:value={eventDescription} type="text" id="eventDescription" name="eventDescription" class="input mb-4">
    <label for="startDate" class="label">Start Date:</label>
    <input bind:value={startDate} type="number" id="startDate" name="startDate" min="1" max="31" class="input mb-4">
    <label for="endDate" class="label">End Date:</label>
    <input bind:value={endDate} type="number" id="endDate" name="endDate" min="1" max="31" class="input mb-4">
    <input type="submit" class="btn variant-ghost-primary" value="Submit">
</form>
