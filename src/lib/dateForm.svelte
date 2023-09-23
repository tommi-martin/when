<script>
    let eventName = '';
    let eventDescription = '';
    let startDate = 0;
    let endDate = 0;
</script>
<form action="#" class="text-black">
    <label for="eventName" class="block mb-2">Event Name:</label>
    <input bind:value={eventName} type="text" id="eventName" name="eventName" class="mb-4">
    <label for="eventDescription" class="block mb-2">Event Description:</label>
    <input bind:value={eventDescription} type="text" id="eventDescription" name="eventDescription" class="mb-4">
    <label for="startDate" class="block mb-2">Start Date:</label>
    <input bind:value={startDate} type="number" id="startDate" name="startDate" min="1" max="31" class="mb-4">
    <label for="endDate" class="block mb-2">End Date:</label>
    <input bind:value={endDate} type="number" id="endDate" name="endDate" min="1" max="31" class="mb-4">
    <input type="submit" value="Submit">
</form>
