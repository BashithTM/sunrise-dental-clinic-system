document.addEventListener('DOMContentLoaded', () => {
    const currentPath = window.location.pathname;
    document.querySelectorAll('.topbar [data-nav-path]').forEach((link) => {
        const modulePath = link.dataset.navPath;
        const isActive = currentPath === modulePath || currentPath.startsWith(`${modulePath}/`);
        link.classList.toggle('active', isActive);
        if (isActive) {
            link.setAttribute('aria-current', 'page');
        }
    });
});
