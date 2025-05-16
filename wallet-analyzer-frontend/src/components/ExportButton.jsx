import html2canvas from "html2canvas";
import jsPDF from "jspdf";

const ExportButton = ({ targetId }) => {
  const handleExport = async (type = "png") => {
    const element = document.getElementById(targetId);
    if (!element) return alert("Export edilecek alan bulunamadı.");

    const canvas = await html2canvas(element, {
      scale: 2,
      backgroundColor: null,
    });

    if (type === "png") {
      const dataURL = canvas.toDataURL("image/png");
      const link = document.createElement("a");
      link.href = dataURL;
      link.download = "wallet-analyzer.png";
      link.click();
    } else if (type === "pdf") {
      const pdf = new jsPDF("p", "mm", "a4");
      const imgData = canvas.toDataURL("image/png");
      const imgProps = pdf.getImageProperties(imgData);
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      pdf.addImage(imgData, "PNG", 0, 0, pdfWidth, pdfHeight);
      pdf.save("wallet-analyzer.pdf");
    }
  };

  return (
    <div className="flex gap-2 mb-4">
      <button
        onClick={() => handleExport("png")}
        className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-1 rounded text-sm"
      >
        📸 PNG Al
      </button>
      <button
        onClick={() => handleExport("pdf")}
        className="bg-green-600 hover:bg-green-700 text-white px-4 py-1 rounded text-sm"
      >
        📄 PDF Al
      </button>
    </div>
  );
};

export default ExportButton;
