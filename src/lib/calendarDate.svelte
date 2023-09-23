<!-- DateComponent.svelte -->
<script>
    /** @type {String} day */
    export let day;
    /** @type {Number|null} */
    export let date;
    /** @type {Boolean} */
    export let selected = false;
    /** @type {Boolean}*/
    export let current = false;

    function handleClick() {
      selected = !selected;
    }
</script>

{#if date !== null}
<div id="date-{date}" class="p-4 border cursor-pointer {current ? "border-red-200" : "border-gray-200"} {selected ? "bg-gray-600" : ""}" on:click={handleClick}>
    <p>{day}</p>
    <p>{date}</p>
</div>
{:else}
<div class="p-4 border-0">
</div>
{/if}
