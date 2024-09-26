
export default function getLabels(component) {
    const filePath = `./src/i18N/${navigator.language}.json`;

    console.log(filePath);
    fetch(filePath).then(response => {
        if (!response.ok) {
            throw new Error(`Error loading ${filePath}`);
        }
        return response.json()
    }).then(data => {
        console.log(data);
    }).catch(error => {
        console.error(error);
    });
} 