<!-- DateComponent.svelte -->
<script>
    export let day;
    export let date;
    // Add your drag and drop logic here
  </script>

<div id="date-{date}" class="p-4 border border-gray-200">
    <p>{day}</p>
    <p>{date}</p>
</div>