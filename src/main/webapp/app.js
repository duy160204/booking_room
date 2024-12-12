async function fetchRoomData() {
    try {
        // Replace the URL with your actual API endpoint
        const response = await fetch('http://localhost:8082/javaweb_btl/roomapi');
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        displayRoomData(data);
    } catch (error) {
        console.error('Error fetching room data:', error);
    }
}

function displayRoomData(data) {
    const container = document.getElementById('room-data');
    let html = `
        <div class="d-flex align-items-center justify-content-start gap-2 my-3">
            <h5 class="mb-0">Bảng các phòng</h5>
            <select class="form-select w-auto" aria-label="Page selection" id="pageSelect">
    `;

    // Dynamically add options for page selection
    for (let i = 1; i <= data.totalPages; i++) {
        html += `
            <option value="${i}" ${i === data.currentPage ? 'selected' : ''}>Trang ${i}</option>
        `;
    }

    html += `
            </select>
            <p class="mb-0">Trang hiện tại: ${data.currentPage}</p>
            <span>trên tổng số ${data.totalPages} trang. Tổng số phòng: ${data.totalItems}</span>
        </div>
    `;

    // Display the room data for the current page
    data.itemsOfCurrentPage.forEach((room) => {
        html += `
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                <div class="room-item shadow rounded overflow-hidden">
                    <div class="position-relative">
                        <img style="width: 100%; height: auto; aspect-ratio: 16 / 9; object-fit: cover;" src="data:image/jpeg;base64,${room.roomImage}" alt="${room.roomName}">
                        <small class="position-absolute start-0 top-100 translate-middle-y bg-primary text-white rounded py-1 px-3 ms-4">
                            ${room.roomPricePerHourVnd}₫/đêm
                        </small>
                    </div>
                    <div class="p-4 mt-2">
                        <div class="d-flex justify-content-between mb-3">
                            <h5 class="mb-0">${room.roomName}</h5>
                            <div class="ps-2">
                                ${Array(room.roomStarCount).fill('<small class="fa fa-star text-primary"></small>').join('')}
                            </div>
                        </div>
                        <div class="d-flex mb-3">
                            <small class="border-end me-3 pe-3">
                                <i class="fa fa-bed text-primary me-2"></i>${room.roomBedCount} giường
                            </small>
                        </div>
                        <p class="text-body mb-3">${room.roomNote}</p>
                        <div class="d-flex justify-content-between">
                            <a class="btn btn-sm btn-dark rounded py-2 px-4" href="#">Book Now</a>
                        </div>
                    </div>
                </div>
            </div>
        `;
    });

    container.innerHTML = html;

    // Event listener to handle page selection change
    document.getElementById('pageSelect').addEventListener('change', (event) => {
        const selectedPage = event.target.value;
        if (selectedPage) {
            window.location.href = `/javaweb_btl/room?page=${selectedPage}`;
        }
    });
}

// Call the function to fetch and display room data
fetchRoomData();
