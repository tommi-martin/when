<!-- DateComponent.svelte -->
<script>
    export let day;
    export let date;
    export let selected = false;
    export let current = false;
</script>

{#if date !== null}
<div id="date-{date}" class="p-4 border  {current ? "border-red-200" : "border-gray-200"} {selected ? "bg-gray-600" : ""}">
    <p>{day}</p>
    <p>{date}</p>
</div>
{:else}
<div class="p-4 border-0">
</div>
{/if}
