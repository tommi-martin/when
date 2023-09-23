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
    /** @type {Boolean} */
    export let disabled = false;

    function handleClick() {
        selected = disabled ? selected : !selected;
    }
</script>

<style>
    .disabled {
        position: relative;
    }
    
    .disabled::before {
        content: "";
        position: absolute;
        top: 50%;
        left: 5px;
        right: 5px;
        border-top: 4px solid rgba(var(--color-error-700));
        transform: rotate(40deg);
    }
    
    .disabled::after {
        content: "";
        position: absolute;
        top: 50%;
        left: 5px;
        right: 5px;
        border-bottom: 4px solid rgba(var(--color-error-700));
        transform: rotate(-40deg);
    }
</style>

{#if date !== null}
<div id="date-{date}" class="{disabled ? "disabled":""} p-4 border cursor-pointer {current ? 'border-red-200' : 'border-gray-200'} {selected ? 'bg-gray-600' : ''}" on:click={handleClick}>
    <p>{day}</p>
    <p>{date}</p>
</div>
{:else}
<div class="p-4 border-0">
</div>
{/if}
